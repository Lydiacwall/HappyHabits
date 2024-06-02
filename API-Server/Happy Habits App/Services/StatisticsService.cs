using DinkToPdf;
using DinkToPdf.Contracts;
using Happy_Habits_App.Forms;
using Happy_Habits_App.PdfConverters;
using Happy_Habits_App.Repositories;
using MailKit.Net.Smtp;
using MailKit.Security;
using MimeKit;

namespace Happy_Habits_App.Services
{
    public class StatisticsService : IStatisticsService
    {
        private string emailServer = "happyhabitscompany@gmail.com";
        private string passwordServer = "tzib fivz ined nvvw";
        private string usernameServer = "Happy Habits";
        private Dictionary<String, IPdfConverter> pdfConverters;
        private readonly IConverter _converter;
        private readonly IMessageRepository _messageRepository;
        private readonly IUserRepository _userRepository;

        public StatisticsService(IConverter converter, IUserRepository userRepository, IMessageRepository messageRepository)
        {
            _converter = converter;
            pdfConverters = new Dictionary<string, IPdfConverter>
            {
                { "Sleep", new SleepPdfConverter(converter) },
                { "FastActivities", new FastActivitiesPdfConverter(converter) },
                { "ExercisesWorkout", new ExercisesWorkoutPdfConverter(converter) },
                { "Weights", new WeightsPdfConverter(converter) },
                { "Symptom", new SymptomPdfConverter(converter) },
                { "Mood", new MoodPdfConverter(converter) },
                { "Food", new FoodPdfConverter(converter) }
            };
            _userRepository = userRepository;
            _messageRepository = messageRepository;
        }

        public async Task GenerateEmail(StatisticsForm form)
        {
            string clientUsername = await _userRepository.GetUsernameById(form.SenderId);

            // Generate PDF from JSON
            var pdfBytes = pdfConverters[form.Type].GeneratePdfFromFormData(form.Statistics, clientUsername);

            // Extract email from formData
            /*string doctorId = await _messageRepository.GetFriendIdByGroupIdAndSenderId(form.GroupId, form.SenderId);

            string email = await _userRepository.GetEmailById(doctorId);*/
            string email = form.Email;

            // TODO CHANGE THIS
            string username = form.friendUsername;

            // Send Email
            await SendEmailWithPdf(username, email, pdfBytes);
        }

        private async Task SendEmailWithPdf(string username, string email, byte[] pdfBytes)
        {
            var message = new MimeMessage();
            message.From.Add(new MailboxAddress(usernameServer, emailServer));
            message.To.Add(new MailboxAddress(username, email));
            message.Subject = "Your client statistics";

            var body = new TextPart("plain")
            {
                Text = "Please find the attached PDF."
            };

            var attachment = new MimePart("application", "pdf")
            {
                Content = new MimeContent(new MemoryStream(pdfBytes)),
                ContentDisposition = new ContentDisposition(ContentDisposition.Attachment),
                ContentTransferEncoding = ContentEncoding.Base64,
                FileName = "form_data.pdf"
            };

            var multipart = new Multipart("mixed");
            multipart.Add(body);
            multipart.Add(attachment);

            message.Body = multipart;

            using var client = new SmtpClient();
            await client.ConnectAsync("smtp.gmail.com", 587, SecureSocketOptions.StartTls);
            await client.AuthenticateAsync(emailServer, passwordServer);
            await client.SendAsync(message);
            await client.DisconnectAsync(true);
        }
    }
}

/*{
  "email": "triantpanos30@gmail.com",
  "message": "Testing new feature",
  "username": "Panos"
}*/