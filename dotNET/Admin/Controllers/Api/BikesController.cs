using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using Admin.Data;
using Admin.Models;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Swashbuckle.AspNetCore.SwaggerGen;

namespace Admin.Controllers.Api
{
    [Authorize]
    [Produces("application/json")]
    [Route("api/bikes")]
    public class BikesController : Controller
    {
        private readonly BikesContext _bikesContext;
        private readonly StationsContext _stationsContext;

        public BikesController(BikesContext bikesContext, StationsContext stationsContext)
        {
            _bikesContext = bikesContext;
            _stationsContext = stationsContext;
        }

        [HttpGet]
        [SwaggerResponse(200, typeof(Bike))]
        public JsonResult GetAllBikes()
        {
            List<Bike> bikes = new List<Bike>();
            foreach (var bike in _bikesContext.Bikes)
            {
                bikes.Add(bike);
            }
            return Json(bikes);
        }

        [HttpPost("add")]
        public IActionResult AddBike([FromBody] Bike bike)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState.Values.SelectMany(v => v.Errors).Select(modelError => modelError.ErrorMessage).ToList());
            }

            Station station = FindStationById(bike.Station);

            if (station == null)
            {
                return BadRequest("Station not found");
            }

            var b = new Bike()
            {
                Id = bike.Id ?? Guid.NewGuid().ToString(),
                Size = bike.Size,
                Station = bike.Station,
                Status = "Returned"
            };

            try
            {
                var result = _bikesContext.Add(b);
                if (result.State == EntityState.Added)
                {
                    _bikesContext.SaveChanges();
                    return Ok();
                }
            }
            catch (SqlException)
            {
                return BadRequest("Incorrect input data");
            }
            catch (DbUpdateException)
            {
                return BadRequest("Incorrect input data");
            }

            return BadRequest();
        }

        [HttpDelete("delete/{id}")]
        public IActionResult DeleteBike(string id)
        {
            try
            {
                Bike bike = FindBikeById(id);
                Station station = FindStationById(bike.Station);
                var result = _bikesContext.Remove(bike);
                station.Bikes -= 1;
                station.FreeRacks += 1;
                var result2 = _stationsContext.Update(station);
                if (result.State == EntityState.Deleted && result2.State == EntityState.Modified)
                {
                    _bikesContext.SaveChanges();
                    _stationsContext.SaveChanges();
                    return Ok();
                }
            }
            catch (ArgumentNullException)
            {
                return BadRequest("Bike not found");
            }
            return BadRequest();
        }

        [HttpPut("rent/{id}")]
        public IActionResult RentBike(string id)
        {
            try
            {
                Bike bike = FindBikeById(id);
                if (bike != null)
                {
                    Station station = FindStationById(bike.Station);
                    bike.Status = "Rented";
                    station.Bikes -= 1;
                    station.FreeRacks += 1;
                    var result = _bikesContext.Update(bike);
                    var result2 = _stationsContext.Update(station);
                    if (result.State == EntityState.Modified && result2.State == EntityState.Modified)
                    {
                        _bikesContext.SaveChanges();
                        _stationsContext.SaveChanges();
                        return Ok();
                    }
                }
            }
            catch (DbUpdateException)
            {
                return BadRequest();
            }
            return BadRequest();
        }

        [HttpPut("return/{bikeId}/{stationId}")]
        public IActionResult ReturnBike(string bikeId, string stationId)
        {
            try
            {
                Bike bike = FindBikeById(bikeId);
                Station station = FindStationById(stationId);
                if (bike != null && station != null)
                {
                    if (station.FreeRacks < 1)
                    {
                        return BadRequest("No free racks");
                    }

                    bike.Status = "Returned";
                    bike.Station = station.Id;
                    station.Bikes += 1;
                    station.FreeRacks -= 1;
                    var result = _bikesContext.Update(bike);
                    var result2 = _stationsContext.Update(station);
                    if (result.State == EntityState.Modified && result2.State == EntityState.Modified)
                    {
                        _bikesContext.SaveChanges();
                        _stationsContext.SaveChanges();
                        return Ok();
                    }
                }
            }
            catch (DbUpdateException)
            {
                return BadRequest();
            }
            return BadRequest();
        }

        private Bike FindBikeById(string id)
        {
            Bike bike = null;
            foreach (var b in _bikesContext.Bikes)
            {
                if (b.Id == id)
                {
                    bike = b;
                    break;
                }
            }
            return bike;
        }

        private Station FindStationById(string id)
        {
            Station station = null;
            foreach (var s in _stationsContext.Stations)
            {
                if (s.Id == id)
                {
                    station = s;
                    break;
                }
            }
            return station;
        }
    }
}