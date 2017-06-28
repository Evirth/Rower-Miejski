using Admin.Models;
using Microsoft.EntityFrameworkCore;

namespace Admin.Data
{
    public class StationsContext : DbContext
    {
        public DbSet<Station> Stations { get; set; }

        public StationsContext(DbContextOptions<StationsContext> options) : base(options) { }

        protected override void OnModelCreating(ModelBuilder modelBulder)
        {
            modelBulder.Entity<Station>().ToTable("Stations");
        }
    }
}
