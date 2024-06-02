namespace Happy_Habits_App.PdfConverters
{
    public interface IPdfConverter
    {
        byte[] GeneratePdfFromFormData(Dictionary<string, object> formData, string clientUsername);
    }
}
