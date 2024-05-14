using Happy_Habits_App.Forms;

namespace Happy_Habits_App.Services
{
    public interface IWeightsActivitiesService
    {
        public Task AddWeightsActivity(WeightsForm form);
    }
}
