using Happy_Habits_App.Forms;
using Happy_Habits_App.Model;
using Happy_Habits_App.Repositories;

namespace Happy_Habits_App.Services
{
    public class ToiletActivitiesService : IToiletActivitiesService
    {
        private readonly IToiletActivitiesRepository _toiletActivitiesRepository;
        public ToiletActivitiesService(IToiletActivitiesRepository toiletActivitiesRepository) => _toiletActivitiesRepository = toiletActivitiesRepository;
        public Task<Toilet> AddToiletActivity(ToiletForm form)
        {
            var toiletActivity = _toiletActivitiesRepository.GetToiletActivityByUserAndIdAndDate(form.UserId, form.HabitId, form.Date);
            return toiletActivity;
        }

        public Task<List<Toilet>> GetAllToiletActivities()
        {
            throw new NotImplementedException();
        }
    }
}
