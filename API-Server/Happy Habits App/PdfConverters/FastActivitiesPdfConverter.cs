using System;
using System.Collections.Generic;
using System.Text.Json;
using DinkToPdf;
using DinkToPdf.Contracts;
using SharpCompress.Common;

namespace Happy_Habits_App.PdfConverters
{
    public class FastActivitiesPdfConverter : IPdfConverter
    {
        private readonly IConverter _converter;

        public FastActivitiesPdfConverter(IConverter converter)
        {
            _converter = converter;
        }

        public byte[] GeneratePdfFromFormData(Dictionary<string, object> formData, string clientUsername)
        {
            string htmlContent = "<html><body><h1>Form Data</h1>";
            htmlContent += $"<h1>{clientUsername}'s FastActivities Statistics</h1>";

            JsonElement element = (JsonElement)formData["averageDuration"];
            var duration = element.GetDouble();
            int hours = (int)duration / 60;
            int minutes = (int)duration % 60;
            htmlContent += $"<p>The client in the month had average duration of <strong> {hours} </strong> hours and <strong> {minutes} </strong> minutes </p>";

            htmlContent += $"<p>The client had an average elevation of <strong>{formData["averageElevation"]}</strong> meters</p>";
            htmlContent += $"<p>The client had an average quantity of <strong>{formData["averageQuantity"]}</strong> kms</p>";
            htmlContent += $"<p>The client had a total quantity of <strong>{formData["totalQuantity"]}</strong> kms</p>";
            htmlContent += $"<p>The client had a total of <strong>{formData["totalWorkouts"]}</strong> workouts</p>";

            htmlContent += "</body></html>";

            var pdfDoc = new HtmlToPdfDocument
            {
                GlobalSettings = { PaperSize = PaperKind.A4 },
                Objects = { new ObjectSettings { HtmlContent = htmlContent } }
            };

            return _converter.Convert(pdfDoc);
        }
    }
}

/*
 {
  "senderId": "6633665f563dbd9d22f06d9d",
  "groupId": "string",
  "type": "FastActivities",
    "statistics": {
        "averageDuration": 510,
        "averageElevation": 7,
        "averageQuantity": 5,
        "totalQuantity": 10,
        "totalWorkouts": 8
    }
}
 */