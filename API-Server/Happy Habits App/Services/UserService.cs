using Happy_Habits_App.Configurations;
using Happy_Habits_App.Forms;
using Happy_Habits_App.Model;
using Happy_Habits_App.Repositories;

using Microsoft.Extensions.Options;
using MongoDB.Bson;
using MongoDB.Driver;

namespace Happy_Habits_App.Services
{
    public class UserService : IUserService
    {
        private readonly IUserRepository _usersRepository;
        public UserService(IUserRepository userRepository)
        {
            _usersRepository = userRepository;
        }

        public async Task<User> GetUserInLoginAsync(string? password, string? email)
        {
            return await _usersRepository.GetUserByPasswordAndEmailAsync(password, email);
        }

        public async Task CreateUserAsync(SignUpModelForm model)
        {
            User user = new User();
            /*user.Username = model.Username;
            user.Password = model.Password;
            user.Email = model.Email;*/

            // Add any business logic/validation here before calling the repository method.
            await _usersRepository.CreateUserAsync(user);
        }
    }
}
