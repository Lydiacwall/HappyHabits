using Happy_Habits_App.Configurations;
using Happy_Habits_App.Model;
using Microsoft.Extensions.Options;
using MongoDB.Driver;

namespace Happy_Habits_App.Repositories
{
    public class SymptomActivitiesRepository : ISymptomActivitiesRepository
    {
        private readonly IMongoCollection<Symptom> _symptomActivitiesCollection;

        public SymptomActivitiesRepository(IOptions<DatabaseSettings> databaseSettings)
        {
            var mongoClient = new MongoClient(databaseSettings.Value.ConnectionURI);
            var mongoDb = mongoClient.GetDatabase(databaseSettings.Value.DatabaseName);
            _symptomActivitiesCollection = mongoDb.GetCollection<Symptom>(databaseSettings.Value.Collections["Symptom"]);
        }

        public async Task CreateSymptomActivityAsync(Symptom symptom)
        {
            await _symptomActivitiesCollection.InsertOneAsync(symptom);
        }

        public async Task<List<Symptom>> GetSymptomsByMonthAndUserAsync(int year, int month, string userId)
        {
            var startDate = new DateOnly(year, month, 1);
            var endDate = startDate.AddMonths(1);

            var filter = Builders<Symptom>.Filter.And(
                Builders<Symptom>.Filter.Gte(symptom => symptom.Date, startDate),
                Builders<Symptom>.Filter.Lt(symptom => symptom.Date, endDate),
                Builders<Symptom>.Filter.Eq(symptom => symptom.UserId, userId)
            );

            var symptoms = await _symptomActivitiesCollection.Find(filter).ToListAsync();
            return symptoms;
        }
    }
}