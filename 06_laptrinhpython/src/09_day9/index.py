from tkinter import *
# root=Tk()
# Label(root,text="Xin chào! Tui là TIen",
# justify=CENTER,relief=SUNKEN).pack(pady=10)
# photo =PhotoImage(file=r"D:\vhvl2024\2. HK2\04_laptrinhpython\01_source\src\09_day9")
# Label(root, image=photo, relief=RAISED).pack(side=LEFT,padx=5)
# root.resizable(height=True,width=True)
# root.minsize(height=300,width=400)
# root.mainloop()

root = Tk()
Label(root, text="Xin chào! Tui là TIen",
      justify=CENTER, relief=SUNKEN).pack(pady=10)

Button(root, text="Khong", fg="red", bg="yellow").pack(pady=10)
Button(root, text="Co", fg="red", bg="yellow").pack(pady=10)
root.mainloop()
