using Happy_Habits_App.Forms;
using Happy_Habits_App.Model;

namespace Happy_Habits_App.Services
{
    public interface IUserService
    {
        public Task<User> GetUserInLoginAsync(string? password, string? email);
        public Task<User?> CreateUserAsync(SignUpModelForm model);

        public Task<User?> FindUserByEmailAsync(string email);
    }
}
