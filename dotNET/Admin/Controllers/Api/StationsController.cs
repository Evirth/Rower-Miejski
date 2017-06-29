using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Threading.Tasks;
using Admin.Data;
using Admin.Models;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Swashbuckle.AspNetCore.SwaggerGen;

namespace Admin.Controllers.Api
{
    [Authorize]
    [Produces("application/json")]
    [Route("api/stations")]
    public class StationsController : Controller
    {
        private readonly StationsContext _stationsContext;

        public StationsController(StationsContext stationsContext)
        {
            _stationsContext = stationsContext;
        }

        [HttpGet]
        [SwaggerResponse(200, typeof(Station))]
        public JsonResult GetAllStations()
        {
            List<Station> stations = new List<Station>();
            foreach (var station in _stationsContext.Stations)
            {
                stations.Add(station);
            }
            return Json(stations);
        }

        [HttpPost("add")]
        public async Task<IActionResult> AddStation([FromBody] Station station)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState.Values.SelectMany(v => v.Errors).Select(modelError => modelError.ErrorMessage).ToList());
            }

            var s = new Station
            {
                Id = station.Id ?? Guid.NewGuid().ToString(),
                Address = station.Address,
                City = station.City,
                Lat = station.Lat,
                Lng = station.Lng,
                Description = station.Description,
                Bikes = station.Bikes,
                FreeRacks = station.FreeRacks
            };

            try
            {
                var result = await _stationsContext.AddAsync(s);
                if (result.State == EntityState.Added)
                {
                    await _stationsContext.SaveChangesAsync();
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
        public async Task<IActionResult> DeleteStation(string id)
        {
            try
            {
                Station station = FindStationById(id);
                if (station == null)
                {
                    return BadRequest("Station not found");
                }

                var result = _stationsContext.Remove(station);
                if (result.State == EntityState.Deleted)
                {
                    await _stationsContext.SaveChangesAsync();
                    return Ok();
                }
            }
            catch (DbUpdateException)
            {
                return BadRequest("Database update error");
            }
            catch (Exception)
            {
                return BadRequest();
            }
            return BadRequest();
        }

        [HttpPut("update/{id}")]
        public async Task<IActionResult> UpdateStation(string id, [FromBody] Station station)
        {
            try
            {
                Station s = FindStationById(id);
                if (s == null)
                {
                    return BadRequest("Station not found");
                }

                s.Address = station.Address ?? s.Address;
                s.City = station.City ?? s.City;
                s.Lat = station.Lat ?? s.Lat;
                s.Lng = station.Lng ?? s.Lng;
                s.Description = station.Description ?? s.Description;
                s.Bikes = station.Bikes ?? s.Bikes;
                s.FreeRacks = station.FreeRacks ?? s.FreeRacks;

                var result = _stationsContext.Update(s);
                if (result.State == EntityState.Modified)
                {
                    await _stationsContext.SaveChangesAsync();
                    return Ok();
                }
            }
            catch (DbUpdateException)
            {
                return BadRequest("Database update error");
            }
            catch (Exception)
            {
                return BadRequest();
            }
            return BadRequest();
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