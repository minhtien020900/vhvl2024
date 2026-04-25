from tkinter import *
window = Tk()
window.title("24810045 Vo Minh Tien")
window.geometry('350x200')
lbl = Label(window, text="Hello").grid(column=0, row=0)
#Tạo một Textbox #Vị trí xuất hiện của Textbox
txt = Entry(window,width=10).grid(column=1, row=0)
print(txt)
#Đặt vị trí con trỏ tại Textbox
# txt.focus()
#Hàm xử lý khi nút được nhấn
def clicked():
    res = "Welcome to " + txt.get()
    lbl.configure(text= res)
btn = Button(window, text="Click Me", command=clicked).grid(column=2, row=0)
# Để tắt chức năng nhập của Textbox bằng state
txt = Entry(window,width=10, state='disabled')
window.mainloop()