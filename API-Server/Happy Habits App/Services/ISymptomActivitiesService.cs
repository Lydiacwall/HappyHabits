using Happy_Habits_App.Forms;

namespace Happy_Habits_App.Services
{
    public interface ISymptomActivitiesService
    {
        public Task AddSymptomActivity(SymptomForm form);
    }
}
