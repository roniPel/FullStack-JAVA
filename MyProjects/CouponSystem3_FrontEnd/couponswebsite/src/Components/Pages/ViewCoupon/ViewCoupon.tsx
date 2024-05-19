import { useParams } from "react-router-dom";
import "./ViewCoupon.css";

export function ViewCoupon(): JSX.Element {
    const params = useParams();
    const IMAGE_WIDTH=200;
    return (
        <div className="ViewCoupon Box">
            <div className="Grid-Parent">
                    <div className="Grid-Child">
                        <img src={params.image} width={IMAGE_WIDTH} />
                    </div>
                    <div className="Grid-Child">
                        <h1>{params.title}</h1>
                        {params.description}<br/>
                        Amount Available: {params.amount}<br/>
                        Valid Until: {params.end_date}<br/>
                        Only {params.price} (NIS)
                    </div>
                </div>
        </div>
    );
}
