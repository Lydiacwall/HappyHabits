using Happy_Habits_App.Forms;
using Happy_Habits_App.Model;
using Happy_Habits_App.Repositories;

namespace Happy_Habits_App.Services
{
    public class MoodActivitiesService : IMoodActivitiesService
    {
        private readonly IMoodActivitiesRepository _moodActivitiesRepository;

        public MoodActivitiesService(IMoodActivitiesRepository moodActivitiesRepository)
        {
            _moodActivitiesRepository = moodActivitiesRepository;
        }

        public async Task AddMoodActivity(MoodForm form)
        {
            await _moodActivitiesRepository.DeleteMoodHabit(form.UserId, DateOnly.Parse(form.Date));
            await _moodActivitiesRepository.CreateMoodActivityAsync(
                new Mood(
                    DateOnly.Parse(form.Date),
                    form.UserId,
                    form.Diary,
                    form.Scale)
                );
        }

        public Task<List<Mood>> GetAllMoodActivities()
        {
            throw new NotImplementedException();
        }
    }
}
