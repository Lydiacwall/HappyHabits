using Happy_Habits_App.Model;

namespace Happy_Habits_App.Repositories
{
    public interface IMedicineRepository
    {
        Task<List<Medicine>> GetMedicinesByDateByUserId(DateOnly date, string? userId);
        Task<List<Medicine>> GetOlderActiveMedicinesByDateByUserId(DateOnly date, string? userId);

        Task UpdateMedicine(Medicine newMedicine);
        public Task<Medicine?> FindOverlappingMedicine(Medicine medicine);

        public Task CreateMedicine(Medicine newMedicine);
        public Task<Medicine?> GetMedicineByUserIdById(string? userId, string? id);
    }
}
