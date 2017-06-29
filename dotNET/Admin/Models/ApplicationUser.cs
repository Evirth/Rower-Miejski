using System;
using System.ComponentModel.DataAnnotations;
using Microsoft.AspNetCore.Identity.EntityFrameworkCore;
using Newtonsoft.Json;
using Newtonsoft.Json.Converters;

namespace Admin.Models
{
    [JsonConverter(typeof(StringEnumConverter))]
    public enum Sex
    {
        Male,
        Female
    }

    public class ApplicationUser : IdentityUser
    {
        [JsonProperty("name")]
        [Required(ErrorMessageResourceType = typeof(Resources.Errors.ModelDataValidation), ErrorMessageResourceName = "EmptyField")]
        public string Name { get; set; }

        [JsonProperty("surname")]
        [Required(ErrorMessageResourceType = typeof(Resources.Errors.ModelDataValidation), ErrorMessageResourceName = "EmptyField")]
        public string Surname { get; set; }

        [JsonProperty("sex")]
        [Required(ErrorMessageResourceType = typeof(Resources.Errors.ModelDataValidation), ErrorMessageResourceName = "EmptyField")]
        public string Sex { get; set; }

        [JsonProperty("registerDate")]
        [Required(ErrorMessageResourceType = typeof(Resources.Errors.ModelDataValidation), ErrorMessageResourceName = "EmptyField")]
        public DateTime RegisterDate { get; set; }

        [JsonProperty("balance")]
        [Required(ErrorMessageResourceType = typeof(Resources.Errors.ModelDataValidation), ErrorMessageResourceName = "EmptyField")]
        public float? Balance { get; set; }
    }
}
