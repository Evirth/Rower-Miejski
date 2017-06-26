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
    [Route("api/[controller]")]
    public class BikesController : Controller
    {
        private readonly BikesContext _bikesContext;

        public BikesController(BikesContext bikesContext)
        {
            _bikesContext = bikesContext;
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

        [HttpPost("Add")]
        public IActionResult AddBike([FromBody] Bike bike)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState.Values.SelectMany(v => v.Errors).Select(modelError => modelError.ErrorMessage).ToList());
            }

            var b = new Bike()
            {
                Id = bike.Id ?? Guid.NewGuid().ToString(),
                Size = bike.Size,
                Station = bike.Station
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

        [HttpDelete("Delete/{id}")]
        public IActionResult DeleteBike(string id)
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

            try
            {
                var result = _bikesContext.Remove(bike);
                if (result.State == EntityState.Deleted)
                {
                    _bikesContext.SaveChanges();
                    return Ok();
                }
            }
            catch (ArgumentNullException)
            {
                return BadRequest("Id not found");
            }
            return BadRequest();
        }
    }
}