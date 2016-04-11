namespace RFIDPrinter
{
    partial class Form1
    {
        /// <summary>
        /// 必需的设计器变量。
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// 清理所有正在使用的资源。
        /// </summary>
        /// <param name="disposing">如果应释放托管资源，为 true；否则为 false。</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows 窗体设计器生成的代码

        /// <summary>
        /// 设计器支持所需的方法 - 不要
        /// 使用代码编辑器修改此方法的内容。
        /// </summary>
        private void InitializeComponent()
        {
            this.btnReadUid = new System.Windows.Forms.Button();
            this.lbUid = new System.Windows.Forms.Label();
            this.button1 = new System.Windows.Forms.Button();
            this.write = new System.Windows.Forms.Button();
            this.read = new System.Windows.Forms.Button();
            this.button2 = new System.Windows.Forms.Button();
            this.infoTextBox = new System.Windows.Forms.TextBox();
            this.groupBox1 = new System.Windows.Forms.GroupBox();
            this.textBox1 = new System.Windows.Forms.TextBox();
            this.retry = new System.Windows.Forms.Button();
            this.label1 = new System.Windows.Forms.Label();
            this.terminate = new System.Windows.Forms.Button();
            this.groupBox1.SuspendLayout();
            this.SuspendLayout();
            // 
            // btnReadUid
            // 
            this.btnReadUid.Location = new System.Drawing.Point(87, 20);
            this.btnReadUid.Name = "btnReadUid";
            this.btnReadUid.Size = new System.Drawing.Size(75, 23);
            this.btnReadUid.TabIndex = 0;
            this.btnReadUid.Text = "获取UID";
            this.btnReadUid.UseVisualStyleBackColor = true;
            this.btnReadUid.Click += new System.EventHandler(this.button1_Click);
            // 
            // lbUid
            // 
            this.lbUid.AutoSize = true;
            this.lbUid.Location = new System.Drawing.Point(26, 82);
            this.lbUid.Name = "lbUid";
            this.lbUid.Size = new System.Drawing.Size(0, 12);
            this.lbUid.TabIndex = 1;
            // 
            // button1
            // 
            this.button1.Location = new System.Drawing.Point(6, 20);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(75, 23);
            this.button1.TabIndex = 2;
            this.button1.Text = "默认配置";
            this.button1.UseVisualStyleBackColor = true;
            this.button1.Click += new System.EventHandler(this.button1_Click_1);
            // 
            // write
            // 
            this.write.Location = new System.Drawing.Point(87, 49);
            this.write.Name = "write";
            this.write.Size = new System.Drawing.Size(75, 23);
            this.write.TabIndex = 3;
            this.write.Text = "写入";
            this.write.UseVisualStyleBackColor = true;
            this.write.Click += new System.EventHandler(this.write_Click);
            // 
            // read
            // 
            this.read.Location = new System.Drawing.Point(6, 49);
            this.read.Name = "read";
            this.read.Size = new System.Drawing.Size(75, 23);
            this.read.TabIndex = 4;
            this.read.Text = "读取";
            this.read.UseVisualStyleBackColor = true;
            this.read.Click += new System.EventHandler(this.read_Click);
            // 
            // button2
            // 
            this.button2.Location = new System.Drawing.Point(41, 71);
            this.button2.Name = "button2";
            this.button2.Size = new System.Drawing.Size(75, 23);
            this.button2.TabIndex = 5;
            this.button2.Text = "测试";
            this.button2.UseVisualStyleBackColor = true;
            this.button2.Click += new System.EventHandler(this.button2_Click_1);
            // 
            // infoTextBox
            // 
            this.infoTextBox.Location = new System.Drawing.Point(200, 7);
            this.infoTextBox.Multiline = true;
            this.infoTextBox.Name = "infoTextBox";
            this.infoTextBox.Size = new System.Drawing.Size(479, 91);
            this.infoTextBox.TabIndex = 6;
            // 
            // groupBox1
            // 
            this.groupBox1.Controls.Add(this.button1);
            this.groupBox1.Controls.Add(this.btnReadUid);
            this.groupBox1.Controls.Add(this.infoTextBox);
            this.groupBox1.Controls.Add(this.button2);
            this.groupBox1.Controls.Add(this.read);
            this.groupBox1.Controls.Add(this.write);
            this.groupBox1.Location = new System.Drawing.Point(32, 12);
            this.groupBox1.Name = "groupBox1";
            this.groupBox1.Size = new System.Drawing.Size(685, 104);
            this.groupBox1.TabIndex = 7;
            this.groupBox1.TabStop = false;
            this.groupBox1.Text = "测试功能";
            // 
            // textBox1
            // 
            this.textBox1.Location = new System.Drawing.Point(238, 145);
            this.textBox1.Multiline = true;
            this.textBox1.Name = "textBox1";
            this.textBox1.ScrollBars = System.Windows.Forms.ScrollBars.Vertical;
            this.textBox1.Size = new System.Drawing.Size(479, 284);
            this.textBox1.TabIndex = 8;
            // 
            // retry
            // 
            this.retry.Location = new System.Drawing.Point(129, 185);
            this.retry.Name = "retry";
            this.retry.Size = new System.Drawing.Size(82, 35);
            this.retry.TabIndex = 9;
            this.retry.Text = "重试";
            this.retry.UseVisualStyleBackColor = true;
            this.retry.Click += new System.EventHandler(this.retry_Click);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(236, 130);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(29, 12);
            this.label1.TabIndex = 10;
            this.label1.Text = "输出";
            // 
            // terminate
            // 
            this.terminate.Location = new System.Drawing.Point(129, 263);
            this.terminate.Name = "terminate";
            this.terminate.Size = new System.Drawing.Size(82, 35);
            this.terminate.TabIndex = 11;
            this.terminate.Text = "终止";
            this.terminate.UseVisualStyleBackColor = true;
            this.terminate.Click += new System.EventHandler(this.terminate_Click);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(765, 460);
            this.Controls.Add(this.terminate);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.retry);
            this.Controls.Add(this.textBox1);
            this.Controls.Add(this.groupBox1);
            this.Controls.Add(this.lbUid);
            this.Name = "Form1";
            this.Text = "Form1";
            this.FormClosed += new System.Windows.Forms.FormClosedEventHandler(this.Form1_FormClosed);
            this.groupBox1.ResumeLayout(false);
            this.groupBox1.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button btnReadUid;
        private System.Windows.Forms.Label lbUid;
        private System.Windows.Forms.Button button1;
        private System.Windows.Forms.Button write;
        private System.Windows.Forms.Button read;
        private System.Windows.Forms.Button button2;
        public System.Windows.Forms.TextBox infoTextBox;
        private System.Windows.Forms.GroupBox groupBox1;
        private System.Windows.Forms.Button retry;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Button terminate;
        public System.Windows.Forms.TextBox textBox1;
    }
}

