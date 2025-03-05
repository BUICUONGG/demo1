# 📝 Todo List App

Một ứng dụng **Todo List** được xây dựng bằng **React**, **Vite**, **Tailwind CSS**, và **localStorage** để lưu trữ dữ liệu.

## 🚀 Tính năng chính

- ✅ Thêm, chỉnh sửa và xóa công việc.
- 🎨 Giao diện thân thiện với người dùng, thiết kế bằng Tailwind CSS.
- 🔄 Lưu trữ công việc cục bộ bằng **localStorage**.
- ⚡ Hiệu suất cao nhờ **Vite**.
- 🔍 Kiểm tra lỗi code với **ESLint**.
- 🧪 Được kiểm thử bằng **Jest** và **React Testing Library**.

---

## 📦 Công nghệ sử dụng

| Công nghệ             | Mô tả                   |
| --------------------- | ----------------------- |
| React 19              | Thư viện UI chính       |
| Vite                  | Công cụ build nhanh     |
| Tailwind CSS          | Framework CSS tiện lợi  |
| Lucide-react          | Bộ icon nhẹ cho React   |
| ESLint                | Linter để giữ code sạch |
| Jest                  | Framework kiểm thử JS   |
| React Testing Library | Công cụ kiểm thử UI     |

---

## 🛠️ Cài đặt và chạy ứng dụng

### 📌 Yêu cầu hệ thống

- **Node.js >= 18**
- **npm >= 8**

### ⏳ Cài đặt dependencies

```sh
npm install
```

### 🚀 Chạy ứng dụng ở môi trường development

```sh
npm run dev
```

Mở trình duyệt và truy cập: **http://localhost:5173**

### 🔧 Build ứng dụng cho production

```sh
npm run build
```

### 🚀 Chạy ứng dụng đã build

```sh
npm run preview
```

---

## 🧪 Hướng dẫn chạy test

Dự án sử dụng **Jest** và **React Testing Library** để kiểm thử component.

### ✅ Chạy toàn bộ test

```sh
npm test
```

🧢 Mô tả Test Case

Dự án sử dụng Jest và React Testing Library để kiểm thử component.

💡 Test Cases

1. Component Rendering:

Kiểm tra xem danh sách todo có hiển thị ban đầu hay không.

Kiểm tra xem các todo item có được render đúng không.

Xác minh sự hiện diện của input field và các núc n\xfat bấm.

2. User Interactions:

Kiểm tra khả năng thêm todo mới.

Kiểm tra xem todo đánh dấu hoàn thành có được cập nhật không.

Kiểm tra chỉnh sửa todo và lưu thay đổi.

Kiểm tra tính năng xóa todo.

Xác minh cơ chế validation cho input field.

3. State Management:

Kiểm tra xem danh sách todo có cập nhật khi thay đổi không.

Kiểm tra chế độ chỉnh sửa có hoạt động đú\xfang không.

Xác minh trạng thái alert khi người dùng thực hiện thao tác.

4. Edge Cases:

Kiểm tra việc nhập todo rỗng.

Kiểm tra validation khi chỉnh sửa todo.

Kiểm tra việc quản lý nhiều todo cùng lúc.

### 📊 Kiểm tra độ phủ code

```sh
npm run test:coverage
```

Sau khi chạy, kết quả sẽ hiển thị coverage của các file.

---

## 📊 Kiểm tra độ phủ test

Dự án sử dụng Jest để đo **test coverage**, gồm các chỉ số chính:

- **Statements**: Bao nhiêu dòng code đã được kiểm thử?
- **Branches**: Bao nhiêu nhánh (if/else) đã được test?
- **Functions**: Bao nhiêu function đã được gọi trong test?
- **Lines**: Tổng số dòng code được kiểm thử.

Kết quả kiểm tra sẽ hiển thị trên terminal hoặc có thể xem chi tiết trong thư mục `coverage/`.

---

## 📂 Cấu trúc thư mục

```
📦 project-root
 ┣ 📂 src
 ┃ ┣ 📂 assets
 ┃ ┃ ┗ 📜 react.svg       # Logo React
 ┃ ┣ 📂 components/ui
 ┃ ┃ ┗ 📜 alert.jsx       # Component Alert
 ┃ ┣ 📜 App.jsx           # Thành phần chính của ứng dụng
 ┃ ┣ 📜 App.css           # Style cho App
 ┃ ┣ 📜 index.css         # Định dạng chung với Tailwind CSS
 ┃ ┣ 📜 main.jsx          # Điểm vào của ứng dụng
 ┣ 📜 index.html          # File HTML chính
 ┣ 📜 eslint.config.js    # Cấu hình ESLint
 ┣ 📜 vite.config.js      # Cấu hình Vite
 ┣ 📜 .gitignore          # Bỏ qua các file không cần thiết
 ┣ 📜 README.md           # Tài liệu hướng dẫn
```

---

## 🌟 Best Practices Áp Dụng

1. **Code Clean & Dễ Bảo Trì**

   - Sử dụng **ESLint** để kiểm tra lỗi code.
   - Đặt tên biến & hàm rõ ràng, tuân theo **camelCase**.
   - Tách component thành các file nhỏ để dễ bảo trì.

2. **Sử dụng React Hooks đúng cách**

   - `useEffect()` chỉ chạy khi cần, tránh lặp vô hạn.
   - `useState()` chỉ lưu trạng thái cần thiết.

3. **Quản lý Style với Tailwind CSS**

   - Sử dụng class tiện lợi, tránh code CSS dư thừa.
   - Tuân theo chuẩn **BEM** nếu cần CSS tùy chỉnh.

4. **Kiểm thử trước khi deploy**
   - Chạy **Jest** để kiểm thử từng component.
   - Đảm bảo **test coverage** ít nhất **80%**.

---

## 📜 License

Dự án này được phát hành dưới giấy phép **MIT License**.
