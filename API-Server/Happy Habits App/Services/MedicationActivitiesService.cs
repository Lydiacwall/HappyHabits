using Happy_Habits_App.Forms;
using Happy_Habits_App.Model;
using Happy_Habits_App.Repositories;
using System;

namespace Happy_Habits_App.Services
{
    public class MedicationActivitiesService : IMedicationActivitiesService
    {
        private readonly IMedicationActivitiesRepository _medicationActivitiesRepository;
        private readonly IMedicineRepository _medicineRepository;

        public MedicationActivitiesService(IMedicationActivitiesRepository medicationActivitiesRepository, IMedicineRepository medicineActivitiesRepository)
        {
            _medicationActivitiesRepository = medicationActivitiesRepository;
            _medicineRepository = medicineActivitiesRepository;
        }

        public async Task<bool> AddMedicine(MedicineForm form)
        {
            // Find if there is a medicine with the same name and the periods collide
            Medicine newMed = new Medicine(
                    form.UserId,
                    form.Name,
                    form.DosageQuantity,
                    form.DosageUnitMeasurement,
                    DateOnly.Parse(form.StartDay),
                    DateOnly.Parse(form.EndDay),
                    form.TimesShouldBeTaken,
                    form.Notes);
            Medicine? medicine = await _medicineRepository.FindOverlappingMedicine(newMed);
            // if yes, then return a message suitable
            if (medicine != null)
            {
                return false;
            }
            // if no, then add it
            await _medicineRepository.CreateMedicine(newMed);
            return true;
        }

        public async Task<List<MedicineDto>> GetTodayMedicines(string userId)
        {
            DateOnly today = new DateOnly(DateTime.Today.Year, DateTime.Today.Month, DateTime.Today.Day);

            // Find medicines that have expired
            List<Medicine> medicines = await _medicineRepository.GetOlderActiveMedicinesByDateByUserId(today, userId);

            // Update old medicines from active to inactive
            foreach (var medicine in medicines)
            {
                medicine.Active = false;
                await _medicineRepository.UpdateMedicine(medicine);
            }

            List<Medicine> medicinesToday = await _medicineRepository.GetMedicinesByDateByUserId(today, userId);

            // Get active medicines only
            List<Medicine> listOfMedicines = medicinesToday.Where(m => m.Active).ToList();

            MedicHabit medicHabit = await _medicationActivitiesRepository.GetMedicHabitByUserIdByDate(today, userId);

            // Initialize list to send
            List<MedicineDto> medicinesToBeSent = new List<MedicineDto>();
            foreach (var medicine in listOfMedicines)
            {
                MedicineDto medDto = new MedicineDto(
                    medicine.Id,
                    medicine.UserId,
                    medicine.Name,
                    medicine.DosageQuantity,
                    medicine.DosageUnitMeasurement,
                    medicine.StartDay.ToString(),
                    medicine.EndDay.ToString(),
                    medicine.TimesShouldBeTaken,
                    0,
                    medicine.Notes);
                if (medicHabit != null)
                {
                    if (medicHabit.Medicines.ContainsKey(medicine.Id))
                    {
                        medDto.TimesTaken = medicHabit.Medicines[medicine.Id].Item1;
                    }
                }
                medicinesToBeSent.Add(medDto);
            }

            return medicinesToBeSent;
        }

        public async Task LogMedication(MedicationForm form)
        {
            // check if there is another medic habit today for the user
            MedicHabit medicHabit = await _medicationActivitiesRepository.GetMedicHabitByUserIdByDate(DateOnly.Parse(form.Date), form.UserId);

            if (medicHabit != null)
            {
                foreach (var medicine in form.Medicines)
                {
                    Medicine? medic = await _medicineRepository.GetMedicineByUserIdById(form.UserId, medicine);
                    if (medic != null)
                    {
                        if (!medicHabit.Medicines.ContainsKey(medicine))
                        {
                            medicHabit.Medicines[medicine] = new Tuple<int, bool>(0, false);
                        }
                        int timesTakenToday = medicHabit.Medicines[medicine].Item1 + 1;
                        bool status = medic.TimesShouldBeTaken == timesTakenToday ? true : false;
                        medicHabit.Medicines[medicine] = new Tuple<int, bool>(timesTakenToday, status);
                    }
                }

                // Update the medic Habit
                await _medicationActivitiesRepository.UpdateMedicHabit(medicHabit);
            }
            else
            {
                MedicHabit newMedic = new MedicHabit(DateOnly.Parse(form.Date), form.UserId);
                foreach (var medicine in form.Medicines)
                {
                    Medicine? medic = await _medicineRepository.GetMedicineByUserIdById(form.UserId, medicine);
                    if (medic != null)
                    {
                        bool _onlyOnce = medic.TimesShouldBeTaken == 1 ? true : false;
                        newMedic.Medicines[medicine] = new Tuple<int, bool>(1, _onlyOnce);
                    }
                }

                // Insert it in the database
                await _medicationActivitiesRepository.CreateMedicHabit(newMedic);
            }
        }

        public async Task RemoveMedicine(RemovalForm form)
        {
            Medicine? medicine = await _medicineRepository.GetMedicineByUserIdById(form.UserId, form.Id);
            if (medicine != null)
            {
                medicine.Active = false;
                await _medicineRepository.UpdateMedicine(medicine);
            }
        }
    }
}