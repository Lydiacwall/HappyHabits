using Happy_Habits_App.Forms;

namespace Happy_Habits_App.Services
{
    public interface IStatisticsService
    {
        Task GenerateEmail(StatisticsForm form);
    }
}
