using Happy_Habits_App.Configurations;
using Happy_Habits_App.Model;
using Microsoft.Extensions.Options;
using MongoDB.Driver;

namespace Happy_Habits_App.Repositories
{
    public class MedicineRepository : IMedicineRepository
    {
        private readonly IMongoCollection<Medicine> _medicineCollection;
        public MedicineRepository(IOptions<DatabaseSettings> databaseSettings)
        {
            var mongoClient = new MongoClient(databaseSettings.Value.ConnectionURI);
            var mongoDb = mongoClient.GetDatabase(databaseSettings.Value.DatabaseName);
            _medicineCollection = mongoDb.GetCollection<Medicine>(databaseSettings.Value.Collections["Medicine"]);
        }
        public async Task<List<Medicine>> GetMedicinesByDateByUserId(DateOnly date, string? userId)
        {
            var filter = Builders<Medicine>.Filter.And(
                Builders<Medicine>.Filter.Lte(x => x.StartDay, date),
                Builders<Medicine>.Filter.Gte(x => x.EndDay, date),
                Builders<Medicine>.Filter.Eq(x => x.UserId, userId),
                Builders<Medicine>.Filter.Eq(x => x.Active, true)
            );

            var medicines = await _medicineCollection.Find(filter).ToListAsync();

            return medicines;
        }

        public async Task<List<Medicine>> GetOlderActiveMedicinesByDateByUserId(DateOnly date, string? userId)
        {
            var filter = Builders<Medicine>.Filter.And(
                Builders<Medicine>.Filter.Lt(x => x.EndDay, date),
                Builders<Medicine>.Filter.Eq(x => x.UserId, userId),
                Builders<Medicine>.Filter.Eq(x => x.Active, true)
            );

            var medicines = await _medicineCollection.Find(filter).ToListAsync();

            return medicines;
        }

        public async Task UpdateMedicine(Medicine medicine)
        {
            var filter = Builders<Medicine>.Filter.Eq(m => m.Id, medicine.Id); // Assuming medicine has an Id property
            var update = Builders<Medicine>.Update
                .Set(m => m.Name, medicine.Name)
                .Set(m => m.DosageQuantity, medicine.DosageQuantity)
                .Set(m => m.DosageUnitMeasurement, medicine.DosageUnitMeasurement)
                .Set(m => m.StartDay, medicine.StartDay)
                .Set(m => m.EndDay, medicine.EndDay)
                .Set(m => m.TimesShouldBeTaken, medicine.TimesShouldBeTaken)
                .Set(m => m.Active, medicine.Active);

            await _medicineCollection.UpdateOneAsync(filter, update);
        }

        public async Task<Medicine?> FindOverlappingMedicine(Medicine medicine)
        {
            // Calculate the start and end dates of the given medicine's period
            DateOnly givenMedicineStartDate = medicine.StartDay;
            DateOnly givenMedicineEndDate = medicine.EndDay;

            // Define the filter to find overlapping medicines
            var filter = Builders<Medicine>.Filter.And(
                Builders<Medicine>.Filter.Eq(x => x.Name, medicine.Name),
                Builders<Medicine>.Filter.Eq(x => x.Active, true),
                Builders<Medicine>.Filter.Or(
                    Builders<Medicine>.Filter.And(
                        Builders<Medicine>.Filter.Lte(x => x.StartDay, givenMedicineStartDate),
                        Builders<Medicine>.Filter.Gte(x => x.EndDay, givenMedicineStartDate)
                    ),
                    Builders<Medicine>.Filter.And(
                        Builders<Medicine>.Filter.Lte(x => x.StartDay, givenMedicineEndDate),
                        Builders<Medicine>.Filter.Gte(x => x.EndDay, givenMedicineEndDate)
                    )
                )
            );

            // Find the overlapping medicine
            var overlappingMedicine = await _medicineCollection.Find(filter).FirstOrDefaultAsync();

            return overlappingMedicine;
        }

        public async Task CreateMedicine(Medicine newMedicine)
        {
            await _medicineCollection.InsertOneAsync( newMedicine );
        }

        public async Task<Medicine?> GetMedicineByUserIdById(string? userId, string? id)
        {
            var filter = Builders<Medicine>.Filter.And(
                Builders<Medicine>.Filter.Eq(m => m.UserId, userId),
                Builders<Medicine>.Filter.Eq(m => m.Id, id)
            );

            return await _medicineCollection.Find(filter).FirstOrDefaultAsync();
        }
    }
}
