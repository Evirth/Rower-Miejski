using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Threading.Tasks;
using Admin.Data;
using Admin.Models;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Identity;
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
        private readonly UserManager<ApplicationUser> _userManager;
        private readonly BikesContext _bikesContext;
        private readonly StationsContext _stationsContext;

        public BikesController(BikesContext bikesContext, StationsContext stationsContext, UserManager<ApplicationUser> userManager)
        {
            _bikesContext = bikesContext;
            _stationsContext = stationsContext;
            _userManager = userManager;
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
        public async Task<IActionResult> AddBike([FromBody] Bike bike)
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
            if (station.FreeRacks < 1)
            {
                return BadRequest("Station is full");
            }

            station.Bikes += 1;
            station.FreeRacks -= 1;

            var b = new Bike
            {
                Id = bike.Id ?? Guid.NewGuid().ToString(),
                Size = bike.Size,
                Station = bike.Station,
                Status = bike.Status ?? "Returned",
                RentedBy = bike.RentedBy
            };

            try
            {
                var result = await _bikesContext.AddAsync(b);
                var result2 = _stationsContext.Update(station);
                if (result.State == EntityState.Added && result2.State == EntityState.Modified)
                {
                    await _bikesContext.SaveChangesAsync();
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
                return BadRequest("Incorrect input data. Size can be only 'Big' or 'Small' and Status 'Returned' or 'Rented'");
            }
            return BadRequest();
        }

        [HttpDelete("delete/{id}")]
        public async Task<IActionResult> DeleteBike(string id)
        {
            try
            {
                Bike bike = FindBikeById(id);
                if (bike == null)
                {
                    return BadRequest("Bike not found");
                }
                Station station = FindStationById(bike.Station);
                var result = _bikesContext.Remove(bike);
                station.Bikes -= 1;
                station.FreeRacks += 1;
                var result2 = _stationsContext.Update(station);
                if (result.State == EntityState.Deleted && result2.State == EntityState.Modified)
                {
                    await _bikesContext.SaveChangesAsync();
                    await _stationsContext.SaveChangesAsync();
                    return Ok();
                }
            }
            catch (ArgumentNullException)
            {
                return BadRequest("Bike not found");
            }
            return BadRequest();
        }

        [HttpPut("update/{id}")]
        public async Task<IActionResult> UpdateBike(string id, [FromBody] Bike bike)
        {
            try
            {
                Bike b = FindBikeById(id);
                if (b == null)
                {
                    return BadRequest("Bike not found");
                }
                
                b.Size = bike.Size;
                b.Station = bike.Station ?? b.Station;
                b.Status = bike.Status ?? b.Status;
                b.RentedBy = bike.RentedBy ?? b.RentedBy;

                var result = _bikesContext.Update(b);
                if (result.State == EntityState.Modified)
                {
                    await _bikesContext.SaveChangesAsync();
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

        [HttpPut("rent/{userId}/{bikeId}")]
        public async Task<IActionResult> RentBike(string userId, string bikeId)
        {
            var user = _userManager.FindByIdAsync(userId).Result;
            if (user == null)
            {
                return BadRequest("User not found");
            }
            if (user.Balance < 0.00f)
            {
                return BadRequest("User's balance is under 10");
            }
            try
            {
                Bike bike = FindBikeById(bikeId);
                if (bike != null)
                {
                    Station station = FindStationById(bike.Station);
                    if (station == null)
                    {
                        return BadRequest("Station not found");
                    }
                    if (station.Bikes < 1)
                    {
                        return BadRequest("No bikes at this station");
                    }

                    bike.Status = "Rented";
                    bike.RentedBy = user.Id;
                    station.Bikes -= 1;
                    station.FreeRacks += 1;
                    var result = _bikesContext.Update(bike);
                    var result2 = _stationsContext.Update(station);
                    if (result.State == EntityState.Modified && result2.State == EntityState.Modified)
                    {
                        await _bikesContext.SaveChangesAsync();
                        await _stationsContext.SaveChangesAsync();
                        return Ok();
                    }
                }
                return BadRequest("Bike not found");
            }
            catch (DbUpdateException)
            {
                return BadRequest();
            }
            catch (Exception)
            {
                return BadRequest();
            }
        }

        [HttpPut("return/{bikeId}/{stationId}")]
        public async Task<IActionResult> ReturnBike(string bikeId, string stationId)
        {
            try
            {
                Bike bike = FindBikeById(bikeId);
                if (bike != null)
                {
                    Station station = FindStationById(stationId);
                    if (station == null)
                    {
                        return BadRequest("Station not found");
                    }
                    if (station.FreeRacks < 1)
                    {
                        return BadRequest("No free racks");
                    }

                    bike.Status = "Returned";
                    bike.Station = station.Id;
                    var user = _userManager.FindByIdAsync(bike.RentedBy).Result;
                    bike.RentedBy = null;
                    station.Bikes += 1;
                    station.FreeRacks -= 1;
                    user.Balance -= 2.00f;

                    var result = _bikesContext.Update(bike);
                    var result2 = _stationsContext.Update(station);
                    if (result.State == EntityState.Modified && result2.State == EntityState.Modified)
                    {
                        await _bikesContext.SaveChangesAsync();
                        await _stationsContext.SaveChangesAsync();
                        await _userManager.UpdateAsync(user);
                        return Ok();
                    }
                }
                return BadRequest("Bike not found");
            }
            catch (DbUpdateException)
            {
                return BadRequest();
            }
            catch (Exception)
            {
                return BadRequest();
            }
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