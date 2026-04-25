n = 0
day_arr = ['Monday','Tuesday','Wednesday','Thursday','Friday','Saturday','Sunday']

temp_arr = []
for i in range(len(day_arr)):
    value = float(input(f"Nhap nhiet do cua {day_arr[i]}: "))
    temp_arr.append(value)

sum_temp = 0
for value in temp_arr:
    sum_temp+=value
    
tmp_avg = sum_temp/len(temp_arr)
list_day_have_temp_greater_avg = []
for value in temp_arr:
    if(value > tmp_avg):
        list_day_have_temp_greater_avg.append(value)
print(f'\nNhiet do trung binh : {tmp_avg}')

print(f'Ds cac ngay co nhiet do > nhiet do TB: {list_day_have_temp_greater_avg}')

