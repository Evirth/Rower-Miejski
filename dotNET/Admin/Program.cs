using System.IO;
using Microsoft.AspNetCore.Hosting;
using Microsoft.Extensions.Configuration;

namespace Admin
{
    public class Program
    {
        public static void Main(string[] args)
        {
            var secretConfig = new ConfigurationBuilder()
                .SetBasePath(Directory.GetCurrentDirectory())
                .AddJsonFile("appsettings.secret.json", optional: true)
                .Build();

            var host = new WebHostBuilder()
                .UseKestrel(options =>
                {
                    var certificate = Path.Combine(secretConfig.GetValue<string>("CertDir"), secretConfig.GetValue<string>("CertName"));
                    options.UseHttps(certificate, secretConfig.GetValue<string>("CertPassword"));
                })
                .UseContentRoot(Directory.GetCurrentDirectory())
                .UseIISIntegration()
                .UseStartup<Startup>()
                .UseApplicationInsights()
                .Build();

            host.Run();
        }
    }
}
