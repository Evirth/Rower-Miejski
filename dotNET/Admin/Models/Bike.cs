using System.ComponentModel.DataAnnotations;
using Newtonsoft.Json;
using Newtonsoft.Json.Converters;

namespace Admin.Models
{
    [JsonConverter(typeof(StringEnumConverter))]
    public enum Size
    {
        Big,
        Small
    }

    [JsonConverter(typeof(StringEnumConverter))]
    public enum BikeStatus
    {
        Returned,
        Rented
    }

    public class Bike
    {
        [JsonProperty("id")]
        public string Id { get; set; }

        [JsonProperty("size")]
        [Required(ErrorMessageResourceType = typeof(Resources.Errors.ModelDataValidation), ErrorMessageResourceName = "EmptyField")]
        public string Size { get; set; }

        [JsonProperty("station")]
        [Required(ErrorMessageResourceType = typeof(Resources.Errors.ModelDataValidation), ErrorMessageResourceName = "EmptyField")]
        public string Station { get; set; }

        [JsonProperty("status")]
        [Required(ErrorMessageResourceType = typeof(Resources.Errors.ModelDataValidation), ErrorMessageResourceName = "EmptyField")]
        public string Status { get; set; }

        [JsonProperty("rentedBy")]
        public string RentedBy { get; set; }
    }
}
