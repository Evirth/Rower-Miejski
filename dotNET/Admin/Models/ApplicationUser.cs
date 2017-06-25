using System;
using Microsoft.AspNetCore.Identity.EntityFrameworkCore;

namespace Admin.Models
{
    public class ApplicationUser : IdentityUser
    {
        public string Name { get; set; }
        public string Surname { get; set; }
        public string Sex { get; set; }
        public DateTime RegisterDate { get; set; }
    }
}
