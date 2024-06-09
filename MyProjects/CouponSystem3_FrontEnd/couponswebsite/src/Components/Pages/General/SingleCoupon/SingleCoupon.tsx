import { useNavigate } from "react-router-dom";
import { Coupon } from "../../../../Models/Coupon";
import "./SingleCoupon.css";
import { Button, ButtonGroup, Card, CardActionArea, CardActions, CardContent, CardMedia, Typography } from "@mui/material";


interface couponProps {
    coupon: Coupon;
}
    
export function SingleCoupon(props: couponProps): JSX.Element {
    const navigate = useNavigate();
    const IMAGE_WIDTH=200;
    return (
        <div id="singleCoupon" className="SingleCoupon Box"
            onClick={()=>{
                navigate(`/coupon/${props.coupon.id.valueOf()}`)
            }}
        >
            <Card sx={{ maxWidth: 345 }}>
                <CardActionArea>
                    <CardMedia
                    component="img"
                    height="140"
                    width={IMAGE_WIDTH}
                    image={props.coupon.image}
                    alt={props.coupon.title}
                    />
                    <CardContent>
                    <Typography gutterBottom variant="h5" component="div">
                    {props.coupon.title}
                    </Typography>
                    <Typography variant="h6" color="text.secondary">
                    {props.coupon.description}
                    </Typography >
                    <Typography variant="body1" color="text.secondary">
                    Valid Until: {props.coupon.end_date}<br/>
                    Only {props.coupon.price} (NIS)<br/>
                    </Typography>
                    </CardContent>
                </CardActionArea>
                <CardActions>
                    <ButtonGroup variant="contained" fullWidth>
                        <Button size="small" color="primary">
                            Buy Now!
                        </Button>
                        <Button size="small" color="secondary">
                            Details
                        </Button>
                    </ButtonGroup>
                </CardActions>
            </Card>
        </div>
    );
}
