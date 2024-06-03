using System;
using System.Collections.Generic;
using System.Linq;
using System.Text.Json;
using DinkToPdf;
using DinkToPdf.Contracts;

namespace Happy_Habits_App.PdfConverters
{
    public class FoodPdfConverter : IPdfConverter
    {
        private readonly IConverter _converter;

        public FoodPdfConverter(IConverter converter)
        {
            _converter = converter;
        }

        public byte[] GeneratePdfFromFormData(Dictionary<string, object> formData, string clientUsername)
        {
            string htmlContent = "<html><body>";
            htmlContent += $"<h1>{clientUsername}'s Nutritional Information</h1>";

            if (formData.TryGetValue("proteinPercentage", out var proteinPercentageElement) &&
                proteinPercentageElement is JsonElement jsonProteinPercentage &&
                jsonProteinPercentage.TryGetSingle(out float proteinPercentage))
            {
                htmlContent += $"<p>Protein Percentage: <strong>{proteinPercentage}%</strong></p>";
            }

            if (formData.TryGetValue("carbsPercentage", out var carbsPercentageElement) &&
                carbsPercentageElement is JsonElement jsonCarbsPercentage &&
                jsonCarbsPercentage.TryGetSingle(out float carbsPercentage))
            {
                htmlContent += $"<p>Carbs Percentage: <strong>{carbsPercentage}%</strong></p>";
            }

            if (formData.TryGetValue("fiberPercentage", out var fiberPercentageElement) &&
                fiberPercentageElement is JsonElement jsonFiberPercentage &&
                jsonFiberPercentage.TryGetSingle(out float fiberPercentage))
            {
                htmlContent += $"<p>Fiber Percentage: <strong>{fiberPercentage}%</strong></p>";
            }

            if (formData.TryGetValue("fatsPercentage", out var fatsPercentageElement) &&
                fatsPercentageElement is JsonElement jsonFatsPercentage &&
                jsonFatsPercentage.TryGetSingle(out float fatsPercentage))
            {
                htmlContent += $"<p>Fats Percentage: <strong>{fatsPercentage}%</strong></p>";
            }

            if (formData.TryGetValue("calories", out var caloriesElement) &&
                caloriesElement is JsonElement jsonCalories &&
                jsonCalories.TryGetSingle(out float calories))
            {
                htmlContent += $"<p>Calories: <strong>{calories}</strong></p>";
            }

            if (formData.TryGetValue("foods", out var foodsElement) &&
                foodsElement is JsonElement jsonFoods &&
                jsonFoods.ValueKind == JsonValueKind.Array)
            {
                htmlContent += "<h2>Food Details</h2>";
                htmlContent += "<table border='1' style='width:100%; border-collapse: collapse;'>";
                htmlContent += "<tr><th>Name</th><th>Meal</th><th>Calories</th><th>Protein</th><th>Fats</th><th>Carbs</th><th>Fiber</th><th>Quantity</th><th>Measurement</th></tr>";

                foreach (JsonElement food in jsonFoods.EnumerateArray())
                {
                    string name = food.GetProperty("name").GetString();
                    string meal = food.GetProperty("meal").GetString();
                    float foodCalories = food.GetProperty("calories").GetSingle();
                    float protein = food.GetProperty("protein").GetSingle();
                    float fats = food.GetProperty("fats").GetSingle();
                    float carbs = food.GetProperty("carbs").GetSingle();
                    float fiber = food.GetProperty("fiber").GetSingle();
                    float quantity = food.GetProperty("quantity").GetSingle();
                    string measurement = food.GetProperty("measurement").GetString();

                    htmlContent += $"<tr><td>{name}</td><td>{meal}</td><td>{foodCalories}</td><td>{protein}</td><td>{fats}</td><td>{carbs}</td><td>{fiber}</td><td>{quantity}</td><td>{measurement}</td></tr>";
                }

                htmlContent += "</table>";
            }

            htmlContent += "</body></html>";

            var pdfDoc = new HtmlToPdfDocument
            {
                GlobalSettings = { PaperSize = PaperKind.A4 },
                Objects = { new ObjectSettings { HtmlContent = htmlContent } }
            };

            return _converter.Convert(pdfDoc);
        }
    }

    public static class JsonElementExtensions
    {
        public static bool TryGetSingle(this JsonElement element, out float result)
        {
            if (element.ValueKind == JsonValueKind.Number && element.TryGetSingle(out result))
            {
                return true;
            }
            result = default;
            return false;
        }
    }
}

/*
 * {
  "proteinPercentage": 25.5,
  "carbsPercentage": 50.0,
  "fiberPercentage": 10.0,
  "fatsPercentage": 14.5,
  "calories": 2000,
  "foods": [
    {
      "Name": "Chicken Breast",
      "Meal": "Lunch",
      "Calories": 300,
      "Protein": 25,
      "Fats": 5,
      "Carbs": 0,
      "Fiber": 0,
      "Quantity": 150,
      "Measurement": "grams"
    },
    {
      "Name": "Broccoli",
      "Meal": "Lunch",
      "Calories": 50,
      "Protein": 4,
      "Fats": 0,
      "Carbs": 10,
      "Fiber": 4,
      "Quantity": 100,
      "Measurement": "grams"
    },
    {
      "Name": "Apple",
      "Meal": "Snack",
      "Calories": 95,
      "Protein": 0,
      "Fats": 0,
      "Carbs": 25,
      "Fiber": 4,
      "Quantity": 1,
      "Measurement": "medium"
    }
  ]
}
*/