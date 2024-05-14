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
    }
}
