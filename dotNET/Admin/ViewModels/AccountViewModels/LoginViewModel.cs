using System.ComponentModel.DataAnnotations;
using Newtonsoft.Json;

namespace Admin.ViewModels.AccountViewModels
{
    public class LoginViewModel
    {
        [JsonProperty("email")]
        [Required(ErrorMessageResourceType = typeof(Resources.Errors.ModelDataValidation), ErrorMessageResourceName = "EmptyField")]
        [EmailAddress(ErrorMessageResourceType = typeof(Resources.Errors.ModelDataValidation), ErrorMessageResourceName = "WrongEmail")]
        public string Email { get; set; }

        [JsonProperty("password")]
        [Required(ErrorMessageResourceType = typeof(Resources.Errors.ModelDataValidation), ErrorMessageResourceName = "EmptyField")]
        [DataType(DataType.Password)]
        public string Password { get; set; }
    }
}
