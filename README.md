

Câu 1
- Cơ chế Service Discovery đóng vai trò như một danh bạ trung tâm (Registry):
  - Đăng ký dịch vụ: Khi mỗi service khởi động, nó tự động gửi thông tin (serviceId , IP, Port, metadata) lên eureka 
    server và định kỳ gửi tín hiệu kiểm tra Heartbeat
  - Tra cứu dịch vụ: khi api gateway hoặc service khác cần gửi một request thay vì lưu cứng IP/Port nó tra cứu 
    eureka theo tên service id. Từ đó load balancer sẽ chọn node tối ưu để gửi request
- Gateway không gọi trực tiếp địa chỉ vì:
  - Ràng buộc vì cấu hình cứng IP/ Port , khi đổi port hoặc triển khai instance mới thì dev buộc phải sửa cấu hình 
    file của gateway, khởi động lại, gây gián đoạn hệ thống
  - mất khả năng cân bằng tải- load balancing
  - Không có cơ chế tự phục hồi: khi một instance chết gateway nếu gọi trực tiếp IP sẽ liên tục bị timeout. Với 
    eureka thì sẽ nhanh chóng gỡ bỏ instance lỗi khỏi danh bạ, tự động chuyển hướng request đến các instance còn 
    sống khác.
Câu 2
- Nếu số lượng request tăng đột biến chúng ta có thể
  - mở rộng theo chiều ngang 
    - khởi chạy thêm nhiều instance mới với các port khác nhau, qua docker, kubernet
  - sử dụng cache (redis): đưa thông tin khoá học phổ biến và kết quả kiểm tra vào redis cache để giảm áp lực gọi 
    sang course service
  - aps dụng rate limiting và circuit breaker tại gateway: giới hạn số request/giây từ client, sử dụng circuit 
    breaker để ngắt mạch khi hệ thống chậm, tránh lỗi dây chuyền
Câu 3
  - Open feign: client gửi request và phải chờ
    - Ưu điểm:
      - Đơn giản trực quan dễ debug
      - phản hồi tức thì cho người dùng
      - chi phí hạ tầng thấp
    - Nhược điểm:
      - Tight coupling
      - Thông lượng bị giới hạn
      - Khó khăn khi scale chịu tải đột biến
  - Apache kafka: producer bắn sự kiện vào topic, lập tức kết thúc lượt xử lý mà không phải chờ
    - Ưu điểm:
      - độc lập tối đa loose coupling
      - hiệu năng và thông lượng cao
      - khả năng chịu lỗi vượt trội
      - dễ mở rộng tính năng mới
    - nhược điểm: 
      - phức tạp về mặt kiến trúc và debug
      - phải xử lý bài toán transaction phân tán 
      - chi phí và tài nguyên lớn