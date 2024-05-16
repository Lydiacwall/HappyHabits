using Happy_Habits_App.Configurations;
using Happy_Habits_App.Model;
using Microsoft.Extensions.Options;
using MongoDB.Driver;

namespace Happy_Habits_App.Repositories
{
    public class WeightsActivitiesRepository : IWeightsActivitiesRepository
    {
        private readonly IMongoCollection<Weights> _weightsActivitiesCollection;

        public WeightsActivitiesRepository(IOptions<DatabaseSettings> databaseSettings)
        {
            var mongoClient = new MongoClient(databaseSettings.Value.ConnectionURI);
            var mongoDb = mongoClient.GetDatabase(databaseSettings.Value.DatabaseName);
            _weightsActivitiesCollection = mongoDb.GetCollection<Weights>(databaseSettings.Value.Collections["Weights"]);
        }

        public async Task CreateWeightsActivity(Weights weights)
        {
            await _weightsActivitiesCollection.InsertOneAsync(weights);
        }
    }
}
