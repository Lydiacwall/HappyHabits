using Happy_Habits_App.Configurations;
using Happy_Habits_App.Repositories;
using Happy_Habits_App.Services;
using Microsoft.AspNetCore.Server.Kestrel.Core;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.
builder.Services.Configure<DatabaseSettings>(builder.Configuration.GetSection("MongoDatabase"));

// User
builder.Services.AddSingleton<IUserService, UserService>();
builder.Services.AddSingleton<IUserRepository, UserRepository>();
// Toilet
builder.Services.AddSingleton<IToiletActivitiesService, ToiletActivitiesService>();
builder.Services.AddSingleton<IToiletActivitiesRepository, ToiletActivitiesRepository>();
// Mood
builder.Services.AddSingleton<IMoodActivitiesService, MoodActivitiesService>();
builder.Services.AddSingleton<IMoodActivitiesRepository, MoodActivitiesRepository>();
// Sleep
builder.Services.AddSingleton<ISleepActivitiesService, SleepActivitiesService>();
builder.Services.AddSingleton<ISleepActivitiesRepository, SleepActivitiesRepository>();

builder.Services.AddControllers();
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

builder.WebHost.ConfigureKestrel(serverOptions =>
{
    serverOptions.Listen(System.Net.IPAddress.Any, 5057);  // Listen for HTTP on port 5057
});

var app = builder.Build();

app.UseCors("AllowAll");

// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}

/*app.UseHttpsRedirection(); // Ensure this is uncommented to enforce HTTPS redirection
*/app.UseAuthorization();
app.MapControllers();
app.Run();
