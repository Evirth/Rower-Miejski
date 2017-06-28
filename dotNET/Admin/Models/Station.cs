using System.ComponentModel.DataAnnotations;
using Newtonsoft.Json;

namespace Admin.Models
{
    public class Station
    {
        [JsonProperty("id")]
        public string Id { get; set; }

        [JsonProperty("address")]
        [Required(ErrorMessageResourceType = typeof(Resources.Errors.ModelDataValidation), ErrorMessageResourceName = "EmptyField")]
        public string Address { get; set; }

        [JsonProperty("city")]
        [Required(ErrorMessageResourceType = typeof(Resources.Errors.ModelDataValidation), ErrorMessageResourceName = "EmptyField")]
        public string City { get; set; }

        [JsonProperty("description")]
        public string Description { get; set; }

        [JsonProperty("bikes")]
        [Required(ErrorMessageResourceType = typeof(Resources.Errors.ModelDataValidation), ErrorMessageResourceName = "EmptyField")]
        public int Bikes { get; set; }

        [JsonProperty("freeRacks")]
        [Required(ErrorMessageResourceType = typeof(Resources.Errors.ModelDataValidation), ErrorMessageResourceName = "EmptyField")]
        public int FreeRacks { get; set; }
    }
}
