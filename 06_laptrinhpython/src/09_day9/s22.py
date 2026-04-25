from tkinter import *
from tkinter.ttk import *
window = Tk()
window.title("Welcome to HCMUTE app")
window.geometry('350x200')
#Thiết lập trạng thái của Checkbox
chk_state = BooleanVar()
chk_state.set(True) #set check state
#Tạo Checkbox có trạng thái đã tích chọn
chk = Checkbutton(window, text='Choose', var=chk_state)
chk.grid(column=0, row=0)
window.mainloop()