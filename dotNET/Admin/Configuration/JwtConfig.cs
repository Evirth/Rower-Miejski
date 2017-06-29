namespace Admin.Configuration
{
    public class JwtConfig
    {
        public string ValidAudience { get; set; }
        public string ValidIssuer { get; set; }
        public string SecretKey { get; set; }
        public int ExpirationTime { get; set; }
    }
}
