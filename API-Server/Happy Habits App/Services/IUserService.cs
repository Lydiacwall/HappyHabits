using Happy_Habits_App.Forms;
using Happy_Habits_App.Model;

namespace Happy_Habits_App.Services
{
    public interface IUserService
    {
        public Task<User> GetUserInLoginAsync(string? password, string? email);
        public Task CreateUserAsync(SignUpModelForm model);
    }
}
