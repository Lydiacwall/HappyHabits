using Happy_Habits_App.Forms;
using Happy_Habits_App.Model;

namespace Happy_Habits_App.Services
{
    public interface IMedicationActivitiesService
    {
        public Task<List<Medicine>> GetTodayMedicines(string userId);
        public Task<bool> AddMedicine(MedicineForm form);
        public Task RemoveMedicine(RemovalForm form);

        public Task LogMedication(MedicationForm form);
    }
}
