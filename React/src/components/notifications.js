import Swal from 'sweetalert2'

export class Notification{

    static successNotification (message) {
        Swal.fire({
            toast: true,
            position: 'top',
            icon: 'success',
            title: message,
            showConfirmButton: false,
            timer: 2000
        })
    }
    
    static failNotification (message) {
        Swal.fire({
            title: message,
            icon: 'error',
            position: 'top',
            toast: true,
            timer: 2000,
            showConfirmButton: false,
            width: '400px',
        })
    }

} 


export default Notification