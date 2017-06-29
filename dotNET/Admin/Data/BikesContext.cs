using Admin.Models;
using Microsoft.EntityFrameworkCore;

namespace Admin.Data
{
    public class BikesContext : DbContext
    {
        public DbSet<Bike> Bikes { get; set; }

        public BikesContext(DbContextOptions<BikesContext> options) : base(options) { }

        protected override void OnModelCreating(ModelBuilder modelBulder)
        {
            modelBulder.Entity<Bike>().ToTable("Bikes");
        }
    }
}
