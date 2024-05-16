using Happy_Habits_App.Model;

namespace Happy_Habits_App.Repositories
{
    public interface IMedicationActivitiesRepository
    {
        public Task<MedicHabit> GetMedicHabitByUserIdByDate(DateOnly date, string userId);

        public Task CreateMedicHabit(MedicHabit medicHabit);

        public Task UpdateMedicHabit(MedicHabit medicHabit);
    }
}
