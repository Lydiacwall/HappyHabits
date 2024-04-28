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

        public async Task<User?> CreateUserAsync(SignUpModelForm model)
        {
            User user = new User();
            user.FirstName = model.FirstName;
            user.LastName = model.LastName;
            user.Password = model.Password;
            user.Email = model.Email;
            user.Birthdate = model.Birthdate;
            user.Speciality = model.Speciality;

            await _usersRepository.CreateUserAsync(user);

            return await _usersRepository.GetUserByEmail(model.Email);
        }

        public async Task<User?> FindUserByEmailAsync(string email)
        {
            //Search if there is another user with the same email
            return await _usersRepository.GetUserByEmail(email);
        }
    }
}
