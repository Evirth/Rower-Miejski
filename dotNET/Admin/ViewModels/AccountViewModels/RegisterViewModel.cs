using System.ComponentModel.DataAnnotations;
using Newtonsoft.Json;

namespace Admin.ViewModels.AccountViewModels
{
    public class RegisterViewModel
    {
        [JsonProperty("email")]
        [Required(ErrorMessageResourceType = typeof(Resources.Errors.ModelDataValidation), ErrorMessageResourceName = "EmptyField")]
        [EmailAddress(ErrorMessageResourceType = typeof(Resources.Errors.ModelDataValidation), ErrorMessageResourceName = "WrongEmail")]
        public string Email { get; set; }

        [JsonProperty("password")]
        [Required(ErrorMessageResourceType = typeof(Resources.Errors.ModelDataValidation), ErrorMessageResourceName = "EmptyField")]
        [StringLength(100, ErrorMessageResourceType = typeof(Resources.Errors.ModelDataValidation), ErrorMessageResourceName = "PasswordLength")]
        [DataType(DataType.Password)]
        public string Password { get; set; }

        [JsonProperty("confirmPassword")]
        [DataType(DataType.Password)]
        [Compare("Password", ErrorMessageResourceType = typeof(Resources.Errors.ModelDataValidation), ErrorMessageResourceName = "PasswordCompare")]
        public string ConfirmPassword { get; set; }
    }
}
