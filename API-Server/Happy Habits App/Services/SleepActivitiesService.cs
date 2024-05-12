using Happy_Habits_App.Forms;
using Happy_Habits_App.Model;
using Happy_Habits_App.Repositories;

namespace Happy_Habits_App.Services
{
    public class SleepActivitiesService : ISleepActivitiesService
    {
        private readonly ISleepActivitiesRepository _sleepActivitiesRepository;
        public SleepActivitiesService(ISleepActivitiesRepository sleepActivitiesRepository)
        {
            _sleepActivitiesRepository = sleepActivitiesRepository;
        }

        public async Task AddSleepActivity(SleepForm form)
        {
            await _sleepActivitiesRepository.DeleteSleepActivityAsync(form.UserId, DateOnly.Parse(form.Date));
            await _sleepActivitiesRepository.CreateSleepActivityAsybc(
                new Sleep(DateOnly.Parse(form.Date), form.UserId, form.Time, form.Quality));
        }

        public Task<List<Sleep>> GetAllSleepActivities()
        {
            throw new NotImplementedException();
        }
    }
}
