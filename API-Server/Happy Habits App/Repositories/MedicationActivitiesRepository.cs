using System;
using System.Collections.Generic;
using System.Threading.Tasks;
using Happy_Habits_App.Configurations;
using Happy_Habits_App.Model;
using Microsoft.Extensions.Options;
using MongoDB.Driver;

namespace Happy_Habits_App.Repositories
{
    public class MedicationActivitiesRepository : IMedicationActivitiesRepository
    {
        private readonly IMongoCollection<MedicHabit> _medicHabitCollection;

        public MedicationActivitiesRepository(IOptions<DatabaseSettings> databaseSettings)
        {
            var mongoClient = new MongoClient(databaseSettings.Value.ConnectionURI);
            var mongoDb = mongoClient.GetDatabase(databaseSettings.Value.DatabaseName);
            _medicHabitCollection = mongoDb.GetCollection<MedicHabit>(databaseSettings.Value.Collections["MedicHabit"]);
        }

        public async Task CreateMedicHabit(MedicHabit medicHabit)
        {
            await _medicHabitCollection.InsertOneAsync(medicHabit);
        }

        public async Task<MedicHabit> GetMedicHabitByUserIdByDate(DateOnly date, string userId)
        {
            // Define your filter based on date and userId
            var filter = Builders<MedicHabit>.Filter.And(
                Builders<MedicHabit>.Filter.Eq(h => h.Date, date),
                Builders<MedicHabit>.Filter.Eq(h => h.UserId, userId)
            );

            // Perform the query and return the result
            return await _medicHabitCollection.Find(filter).FirstOrDefaultAsync();
        }

        public async Task UpdateMedicHabit(MedicHabit medicHabit)
        {
            var filter = Builders<MedicHabit>.Filter.Eq(h => h.Id, medicHabit.Id);

            await _medicHabitCollection.ReplaceOneAsync(filter, medicHabit);
        }
    }
}

