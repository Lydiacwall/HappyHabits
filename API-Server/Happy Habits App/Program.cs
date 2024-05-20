using Happy_Habits_App.Configurations;
using Happy_Habits_App.Hubs;
using Happy_Habits_App.Repositories;
using Happy_Habits_App.Services;
using Microsoft.AspNetCore.Http.Connections;
using Microsoft.AspNetCore.Server.Kestrel.Core;
using MongoDB.Bson.Serialization;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.
builder.Services.Configure<DatabaseSettings>(builder.Configuration.GetSection("MongoDatabase"));

// Register the custom tuple serializer
BsonSerializer.RegisterSerializer(typeof(Tuple<string, string>), new TupleSerializer<string, string>());

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
// Fast Activities
builder.Services.AddSingleton<IFastActivitiesService, FastActivitiesService>();
builder.Services.AddSingleton<IFastActivitiesRepository, FastActivitiesRepository>();
// Weights
builder.Services.AddSingleton<IWeightsActivitiesService, WeightsActivitiesService>();
builder.Services.AddSingleton<IWeightsActivitiesRepository, WeightsActivitiesRepository>();
// Weights
builder.Services.AddSingleton<IExercisesWorkoutActivitiesService, ExercisesWorkoutActivitiesService>();
builder.Services.AddSingleton<IExercisesWorkoutActivitiesRepository, ExercisesWorkoutActivitiesRepository>();
// Medicines - Medic Habit
builder.Services.AddSingleton<IMedicationActivitiesService, MedicationActivitiesService>();
builder.Services.AddSingleton<IMedicationActivitiesRepository, MedicationActivitiesRepository>();
builder.Services.AddSingleton<IMedicineRepository, MedicineRepository>();
// Messages
builder.Services.AddSingleton<IMessageRepository, MessageRepository>();
builder.Services.AddSingleton<IMessageService,  MessageService>();
// Symptom
builder.Services.AddSingleton<ISymptomActivitiesRepository, SymptomActivitiesRepository>();
builder.Services.AddSingleton<ISymptomActivitiesService, SymptomActivitiesService>();
// Food
builder.Services.AddSingleton<IFoodActivitiesRepository, FoodActivitiesRepository>();
builder.Services.AddSingleton<IFoodActivitiesService, FoodActivitiesService>();

builder.Services.AddControllers();
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();
builder.Services.AddSignalR(); 

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

// Map SignalR hub
app.MapHub<ChatHub>("/chatHub", options =>
{
    options.Transports = HttpTransportType.WebSockets;
});

/*app.UseHttpsRedirection(); // Ensure this is uncommented to enforce HTTPS redirection
*/
app.UseAuthorization();
app.MapControllers();
app.Run();
