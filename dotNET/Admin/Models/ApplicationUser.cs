using System;
using System.Runtime.Serialization;
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
        public string Name { get; set; }
        public string Surname { get; set; }
        public string Sex { get; set; }
        public DateTime RegisterDate { get; set; }
    }
}
