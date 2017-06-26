using System.ComponentModel.DataAnnotations;
using Admin.Models;
using Admin.Models.Validation;
using Newtonsoft.Json;
using Newtonsoft.Json.Converters;

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
        [Required(ErrorMessageResourceType = typeof(Resources.Errors.ModelDataValidation), ErrorMessageResourceName = "EmptyField")]
        [Compare("Password", ErrorMessageResourceType = typeof(Resources.Errors.ModelDataValidation), ErrorMessageResourceName = "PasswordCompare")]
        public string ConfirmPassword { get; set; }

        [JsonProperty("phoneNumber")]
        [Required(ErrorMessageResourceType = typeof(Resources.Errors.ModelDataValidation), ErrorMessageResourceName = "EmptyField")]
        [PhoneNumber(ErrorMessageResourceType = typeof(Resources.Errors.ModelDataValidation), ErrorMessageResourceName = "InvalidPhoneNumber")]
        public string PhoneNumber { get; set; }

        [JsonProperty("name")]
        [Required(ErrorMessageResourceType = typeof(Resources.Errors.ModelDataValidation), ErrorMessageResourceName = "EmptyField")]
        public string Name { get; set; }

        [JsonProperty("surname")]
        [Required(ErrorMessageResourceType = typeof(Resources.Errors.ModelDataValidation), ErrorMessageResourceName = "EmptyField")]
        public string Surname { get; set; }

        [JsonProperty("sex")]
        [Required(ErrorMessageResourceType = typeof(Resources.Errors.ModelDataValidation), ErrorMessageResourceName = "EmptyField")]
        public string Sex { get; set; }
    }
}
