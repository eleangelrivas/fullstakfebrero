import Swal from "sweetalert2";
export const showLoading = (message,text) => {
    let timerInterval;
    Swal.fire({
    title: message,
    html: text,
    // timer: 2000,
    timerProgressBar: true,
    didOpen: () => {
        Swal.showLoading();
        
    },
    willClose: () => {
        
    }
    }).then((result) => {
    /* Read more about handling dismissals below */
    if (result.dismiss === Swal.DismissReason.timer) {
        console.log("I was closed by the timer");
    }
    });
};