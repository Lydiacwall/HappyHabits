using Happy_Habits_App.Configurations;
using Happy_Habits_App.Model;
using Microsoft.Extensions.Options;
using MongoDB.Driver;

namespace Happy_Habits_App.Repositories
{
    public class ExercisesWorkoutActivitiesRepository : IExercisesWorkoutActivitiesRepository
    {
        private readonly IMongoCollection<ExercisesWorkout> _exercisesWorkoutActivitiesCollection;

        public ExercisesWorkoutActivitiesRepository(IOptions<DatabaseSettings> databaseSettings)
        {
            var mongoClient = new MongoClient(databaseSettings.Value.ConnectionURI);
            var mongoDb = mongoClient.GetDatabase(databaseSettings.Value.DatabaseName);
            _exercisesWorkoutActivitiesCollection = mongoDb.GetCollection<ExercisesWorkout>(databaseSettings.Value.Collections["ExercisesWorkout"]);
        }

        public async Task CreateExerciseWorkoutActivity(ExercisesWorkout exercisesWorkout)
        {
            await _exercisesWorkoutActivitiesCollection.InsertOneAsync(exercisesWorkout);
        }

        public async Task<List<ExercisesWorkout>> GetExercisesWorkoutActivitiesByUserAndDate(string userId, int month, int year, string type)
        {
            var startDate = new DateOnly(year, month, 1);
            var endDate = startDate.AddMonths(1).AddDays(-1);

            var filterBuilder = Builders<ExercisesWorkout>.Filter;
            var filter = filterBuilder.And(
                filterBuilder.Eq(ew => ew.UserId, userId),
                filterBuilder.Gte(ew => ew.Date, startDate),
                filterBuilder.Lte(ew => ew.Date, endDate),
                filterBuilder.Eq(ew => ew.Type, type)
            );

            return await _exercisesWorkoutActivitiesCollection.Find(filter).ToListAsync();
        }

    }
}
