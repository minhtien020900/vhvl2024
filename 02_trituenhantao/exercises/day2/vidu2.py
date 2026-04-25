#list
ds1 = [1,2,3,'bon','nam']


#for name in ds1: 
   # print(name)
    

#While
ds = [
    'Nguyen Van A',
    'Nguyen Van C',
    'Nguyen Van B',
]

# Ham - Function
def timSv(ds,tuKhoa):
    i = 0
    while ds[i].split(' ')[-1]!=tuKhoa:
        i+=1
    return ds[i]
#print(timSv(ds,'B'))

if __name__ == '__main__':
    for i in range(len(ds)): 
        print(ds[i])
