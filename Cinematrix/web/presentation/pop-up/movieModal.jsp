<%-- 
    Document   : movieModal
    Created on : 27/05/2021, 05:04:05 PM
    Author     : Marvin Aguilar
--%>

<!-- MOVIE POPUP -->
<div class="modal fade" id="movieModal" tabindex="-1" aria-labelledby="movieLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title col-11 text-center text-primary" id="movieLabel">Spider Man - May 29,03pm /
                    B2
                </h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form name="" action="" method="">
                    <div class="col text-center mb-3">
                        <div class="card">
                            <img class="card-img-top" src="/Cinematrix/images/Spiderman.png" alt="image">
                            <div class="card-body text-center">
                                <h6 class="card-text">Price: $10</h5>
                            </div>
                        </div>
                    </div>

                    <div class="col booking-container">
                        <h6 class="text-primary">Seats</h6>
                        <ul class="showcase">
                            <li>
                                <div class="seat"></div>
                                <small>Available</small>
                            </li>
                            <li>
                                <div class="seat selected"></div>
                                <small>Selected</small>
                            </li>
                            <li>
                                <div class="seat occupied"></div>
                                <small>Occupied</small>
                            </li>
                        </ul>

                        <div class="cinema">
                            <div class="screen"></div>

                            <div class="row-seats">
                                <div class="seat"></div>
                                <div class="seat"></div>
                                <div class="seat"></div>
                                <div class="seat"></div>
                                <div class="seat"></div>
                                <div class="seat"></div>
                                <div class="seat"></div>
                                <div class="seat"></div>
                            </div>

                            <div class="row-seats">
                                <div class="seat"></div>
                                <div class="seat"></div>
                                <div class="seat occupied"></div>
                                <div class="seat occupied"></div>
                                <div class="seat"></div>
                                <div class="seat"></div>
                                <div class="seat"></div>
                                <div class="seat"></div>
                            </div>

                            <div class="row-seats">
                                <div class="seat"></div>
                                <div class="seat"></div>
                                <div class="seat"></div>
                                <div class="seat"></div>
                                <div class="seat"></div>
                                <div class="seat"></div>
                                <div class="seat occupied"></div>
                                <div class="seat occupied"></div>
                            </div>

                            <div class="row-seats">
                                <div class="seat"></div>
                                <div class="seat"></div>
                                <div class="seat"></div>
                                <div class="seat"></div>
                                <div class="seat"></div>
                                <div class="seat"></div>
                                <div class="seat"></div>
                                <div class="seat"></div>
                            </div>

                            <div class="row-seats">
                                <div class="seat"></div>
                                <div class="seat"></div>
                                <div class="seat"></div>
                                <div class="seat occupied"></div>
                                <div class="seat occupied"></div>
                                <div class="seat"></div>
                                <div class="seat"></div>
                                <div class="seat"></div>
                            </div>

                            <div class="row-seats">
                                <div class="seat"></div>
                                <div class="seat"></div>
                                <div class="seat"></div>
                                <div class="seat"></div>
                                <div class="seat occupied"></div>
                                <div class="seat occupied"></div>
                                <div class="seat occupied"></div>
                                <div class="seat"></div>
                            </div>
                        </div>

                    </div>

                    <div class="col text-center">
                        <h6>You have selected <span class="text-primary">2</span> seats for a price of <span
                                class="text-primary">$20</span></h6>
                    </div>


                </form>
            </div>
            <div class="modal-footer">
                <div class="col text-center">
                    <button type="button" class="btn btn-primary w-25">Purchase</button>
                </div>
            </div>
        </div>
    </div>
</div>
