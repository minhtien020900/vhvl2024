if __name__ == '__main__':
    def dog_age(human_age):
        convert_human_age = float(human_age)
        if float(convert_human_age)<=2:
            return convert_human_age*10.5
        elif float(convert_human_age) > 2:
            return 2*10.5 + (convert_human_age - 2) *4


    def noise_source(level):
        level_parsed = int(level) 
        
        if level_parsed < 40:
            return "Nho hon Căn phòng yên tĩnh"
        if level_parsed > 130:
            return "Lon hon Búa khoan"
        if level_parsed > 40 and level_parsed < 70:
            return "Lon hon Căn phòng yên tĩnh  va nho hon Đồng hồ báo thức"
        if level_parsed > 70 and level_parsed < 106:
            return "Lon hon Đồng hồ báo thức va nho hon Máy xén cỏ chạy xăng"
        if level_parsed > 106 and level_parsed < 130:
            return "Lon hon Máy xén cỏ chạy xăng va nho hon Máy xén cỏ Búa khoan"
    
    def square_color(pos):
        row = ['1','2','3','4','5','6','7','8']
        column = ['a','b','c','d','e','f','g','h']
        pos_map_color_dict = {}
        
        for index_row, row_item in enumerate(row):
            for index_column,column_item in enumerate(column):
                key = str(column_item+row_item)
                if((index_column + index_row) % 2 == 0):
                    pos_map_color_dict[key] = "Đen"
                else:
                    pos_map_color_dict[key] = "Trắng"
            
        return pos_map_color_dict[pos]
    
    print(dog_age(input("Input human age: ")))
    print(noise_source(input("Input level: ")))
    print(square_color(input("Nhập vị trí: ")))
