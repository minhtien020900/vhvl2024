namespace OOP_ButtonClick
{
    partial class Form1
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.textBox1 = new System.Windows.Forms.TextBox();
            this.btnRender = new System.Windows.Forms.Button();
            this.label1 = new System.Windows.Forms.Label();
            this.renderBtnArea = new System.Windows.Forms.FlowLayoutPanel();
            this.SuspendLayout();
            // 
            // textBox1
            // 
            this.textBox1.Location = new System.Drawing.Point(112, 32);
            this.textBox1.Name = "textBox1";
            this.textBox1.Size = new System.Drawing.Size(109, 22);
            this.textBox1.TabIndex = 0;
            // 
            // btnRender
            // 
            this.btnRender.Location = new System.Drawing.Point(241, 28);
            this.btnRender.Name = "btnRender";
            this.btnRender.Size = new System.Drawing.Size(80, 30);
            this.btnRender.TabIndex = 1;
            this.btnRender.Text = "Click";
            this.btnRender.UseVisualStyleBackColor = true;
            this.btnRender.Click += new System.EventHandler(this.btnRender_Click);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(12, 35);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(94, 16);
            this.label1.TabIndex = 2;
            this.label1.Text = "Nhập số lượng";
            // 
            // renderBtnArea
            // 
            this.renderBtnArea.Location = new System.Drawing.Point(15, 105);
            this.renderBtnArea.Name = "renderBtnArea";
            this.renderBtnArea.Size = new System.Drawing.Size(773, 321);
            this.renderBtnArea.TabIndex = 3;
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 450);
            this.Controls.Add(this.renderBtnArea);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.btnRender);
            this.Controls.Add(this.textBox1);
            this.Name = "Form1";
            this.Text = "Form1";
            this.Load += new System.EventHandler(this.Form1_Load);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.TextBox textBox1;
        private System.Windows.Forms.Button btnRender;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.FlowLayoutPanel renderBtnArea;
    }
}

